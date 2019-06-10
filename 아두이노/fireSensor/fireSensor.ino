
#include <ESP8266WiFi.h>
#include <ArduinoJson.h>


const char* ssid     = "qwe12";      //  ID
const char* password = "11223344";      //  비밀번호

const char* host = "192.168.43.152";       // 서버 IP

//gpio= 0, 2, 4, 5, 12, 13, 14, 15, 16
const int flame16 = 16;
const int flame2 = 2;
const int flame4 = 4;
const int flame5 = 5;
const int flame12 = 12;
const int flame13 = 13;
const int flame14 = 14;

void setup() {
  pinMode(flame2, INPUT);
  pinMode(flame4, INPUT);
  pinMode(flame5, INPUT);
  pinMode(flame12, INPUT);
  pinMode(flame13, INPUT);
  pinMode(flame14, INPUT);
  pinMode(flame16, INPUT);

  Serial.begin(115200);
  delay(10);

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println(); 
  Serial.print("Connecting to ");
  Serial.println(ssid);
 // ESP.Disable();
  if(!WiFi.getAutoConnect()){

    WiFi.begin(ssid, password);
  }
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

int value = 0;

void loop() {                        /// Loop 시작
 
  ++value;

  Serial.print("connecting to ");
  Serial.println(host);
  
  // Use WiFiClient class to create TCP connections
  WiFiClient client;
  const int httpPort=8080;
  Serial.println(host);
  Serial.println(httpPort);
  
  if (!client.connect(host, httpPort)) {
    Serial.println("connection failed");
    return;
  }
  // We now create a URI for the request
  String url = "/SFE/updateFireSensor";
 
  
  Serial.print("Requesting URL: ");
  Serial.println(url);
//gpio= 0, 2, 4, 5, 12, 13, 14, 15, 16
  //불꽃센서
  //state == 0이면 on

  int state2 = digitalRead(flame2);
  int state4 = digitalRead(flame4);
  int state5 = digitalRead(flame5);
  int state12 = digitalRead(flame12);
  int state13 = digitalRead(flame13);
  int state14 = digitalRead(flame14);
  int state16 = digitalRead(flame16);


  String jsondata = "";
  StaticJsonBuffer<200> jsonBuffer;
  JsonObject& root = jsonBuffer.createObject();
  if(state16 == 0)root["id0"] = 0;
    else root["id0"] = 999;
  if(state2 == 0)root["id1"] = 1;
    else root["id1"] = 999;
  if(state4 == 0)root["id2"] = 2;
    else root["id2"] = 999;
  if(state5 == 0)root["id3"] = 3;
    else root["id3"] = 999;
  if(state12 == 0)root["id4"] = 4;
    else root["id4"] = 999;
  if(state13 == 0)root["id5"] = 5;
    else root["id5"] = 999;
  if(state14 == 0)root["id6"] = 6;
    else root["id6"] = 999;


  root.printTo(jsondata);
  Serial.println(jsondata);              // 측정한 센서 값 전송.
  
  client.print(String("POST ") + url + " HTTP/1.1\r\n" +
               "Host: " + host +":8080\r\n");
  client.print("User-Agent: Arduino\r\n");
  client.print("Content-Type: application/json\r\n");
  client.print("Content-Length: ");
  client.println(jsondata.length());
  client.println();
  client.println(jsondata);
  client.print("Connection: close\r\n\r\n");
  
  unsigned long timeout = millis();
  while (client.available() == 0) {
    if (millis() - timeout > 4000) {
      Serial.println(">>> Client Timeout !");
      client.stop();
      return;
    }

  }
  
  // Read all the lines of the reply from server and print them to Serial
  while(client.available()==0){
    String line = client.readStringUntil('\r');
    Serial.print(line);
  }
  
  Serial.println();
  Serial.println("closing connection");
  
  delay(1000);
}
