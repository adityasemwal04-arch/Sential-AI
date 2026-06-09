/****************************************************
 SMART HELMET WITH ACCIDENT DETECTION & AUTO SOS
 Platform : ESP32
 IDE      : VS Code (PlatformIO)
 ****************************************************/

#include <Arduino.h>
#include <Wire.h>
#include <Adafruit_MPU6050.h>
#include <Adafruit_Sensor.h>
#include <TinyGPS++.h>

/* ---------------- PIN CONFIGURATION ---------------- */
#define BUZZER_PIN 12
#define LED_PIN    13

#define GSM_RX 16
#define GSM_TX 17

#define GPS_RX 4
#define GPS_TX 5

/* ---------------- OBJECT DECLARATION ---------------- */
Adafruit_MPU6050 mpu;
TinyGPSPlus gps;

HardwareSerial gsm(1);
HardwareSerial gpsSerial(2);

/* ---------------- THRESHOLD VALUES ---------------- */
#define IMPACT_THRESHOLD 5.0     // 4g – 7g (as per report)
#define IMMOBILITY_TIME 2000     // 2 seconds

bool accidentFlag = false;
unsigned long impactTime = 0;

float latitude = 0.0;
float longitude = 0.0;

/* ---------------- FUNCTION DECLARATIONS ---------------- */
void getGPSLocation();
void sendSOS();
void alertUser();

/* ===================================================== */
void setup() {
  Serial.begin(9600);

  pinMode(BUZZER_PIN, OUTPUT);
  pinMode(LED_PIN, OUTPUT);

  /* Initialize MPU6050 */
  if (!mpu.begin()) {
    Serial.println("MPU6050 Not Detected!");
    while (1);
  }

  /* Initialize UARTs */
  gpsSerial.begin(9600, SERIAL_8N1, GPS_RX, GPS_TX);
  gsm.begin(9600, SERIAL_8N1, GSM_RX, GSM_TX);

  Serial.println("Smart Helmet System Started");
}

/* ===================================================== */
void loop() {
  sensors_event_t accel, gyro, temp;
  mpu.getEvent(&accel, &gyro, &temp);

  /* Convert acceleration to 'g' */
  float ax = accel.acceleration.x / 9.8;
  float ay = accel.acceleration.y / 9.8;
  float az = accel.acceleration.z / 9.8;

  float magnitude = sqrt(ax * ax + ay * ay + az * az);

  Serial.print("Acceleration (g): ");
  Serial.println(magnitude);

  /* -------- STAGE 1: IMPACT DETECTION -------- */
  if (magnitude > IMPACT_THRESHOLD && !accidentFlag) {
    accidentFlag = true;
    impactTime = millis();
    Serial.println("High Impact Detected");
  }

  /* -------- STAGE 2: IMMOBILITY CHECK -------- */
  if (accidentFlag && (millis() - impactTime > IMMOBILITY_TIME)) {
    Serial.println("Immobility Confirmed");

    getGPSLocation();
    sendSOS();
    alertUser();

    accidentFlag = false;
  }

  delay(200);
}

/* ===================================================== */
void getGPSLocation() {
  unsigned long startTime = millis();

  while (millis() - startTime < 5000) {
    while (gpsSerial.available()) {
      gps.encode(gpsSerial.read());
      if (gps.location.isUpdated()) {
        latitude = gps.location.lat();
        longitude = gps.location.lng();
        Serial.println("GPS Location Acquired");
        return;
      }
    }
  }

  Serial.println("GPS Timeout – Using Last Known Location");
}

/* ===================================================== */
void sendSOS() {
  gsm.println("AT");
  delay(1000);

  gsm.println("AT+CMGF=1");
  delay(1000);

  gsm.println("AT+CMGS=\"+91XXXXXXXXXX\""); // Emergency Number
  delay(1000);

  gsm.print("Accident Detected!\n");
  gsm.print("Location:\n");
  gsm.print("https://maps.google.com/?q=");
  gsm.print(latitude, 6);
  gsm.print(",");
  gsm.print(longitude, 6);

  gsm.write(26);  // CTRL+Z
  delay(3000);

  Serial.println("SOS Message Sent");
}

/* ===================================================== */
void alertUser() {
  digitalWrite(LED_PIN, HIGH);
  digitalWrite(BUZZER_PIN, HIGH);
  delay(3000);
  digitalWrite(LED_PIN, LOW);
  digitalWrite(BUZZER_PIN, LOW);
}