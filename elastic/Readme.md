# Readme.md

Installation:

 * git clone https://github.com/payamrastogi/gfl-challenge.git
 * cd gfl-challenge/elastic
 * modify config.properties in (gfl-elastic, gfl-client, gfl-sfbay, gfl-service)
 * rename install.txt to install.sh
 * rename ngrok.txt to ngrok (for testing on localhost)
 * execute the following commands on the terminal (current working directory : gfl-challenge/elastic)
```
chmod +x install.sh
./install.sh
chmod +x ngrok
```
 * on the separate terminal execute the following command
 ``` 
 ./ngrok http [port_of_gfl-service]
 ```
 * copy the forwarding address and paste it in the slack slash command intgration setting's url field.

![ngrok](https://github.com/payamrastogi/gfl-challenge/blob/master/elastic/screenshots/Screenshot%202017-11-18%2015.13.18.png "ngrok")

![slack slash command](https://github.com/payamrastogi/gfl-challenge/blob/master/elastic/screenshots/Screenshot%202017-11-18%2015.14.35.png "slash command")


Description

The current folder contains 5 different projects

![components](https://github.com/payamrastogi/gfl-challenge/blob/master/elastic/screenshots/Screenshot%202017-11-18%2015.17.54.png "components")

* gfl-elastic:  It interacts with the  elastic search service provided by greenfieldlabs and get the agency name against the stopcode. (Deafult Port: 8881)

Request
```
GET /gfl/elastic/search/stopCode/:stopCode
```
Response
```
{
	"status":"SUCCESS",
	"data":
          {
          "stopCode":"51130",
          "agencyName":"AC Transit"
          }
}
```

* gfl-sfbay: It interacts with the SF Bay Area transit. (Default Port: 8882)
* It fetches 
   - The agency code given the agency name
   - Predicts the stop given stop code and agency code

Request

```
GET /gfl/sfbay/search/agencyName/:agencyName
```
Response
```
{
	"status":"SUCCESS",
    "data":
    		{
    			"agencyName":"AC Transit",
                "agencyCode":"AC"
             }
}
```

Request
```
GET /gfl/sfbay/search/:agencyCode/:stopCode
```

Response
```
{
	"status":"SUCCESS",
    "data":	[
    			{"agencyCode":"AC",
                "stopCode":"51130",
                "busNo":"232",
                "arrivalTime":"2017-11-18T21:10:00Z"
                }
            ]
}
```

* gfl-service: It interacts with gfl-elastic and gfl-sfbay components. (Default Port: 8883)

Request
```
GET /gfl/search/stopCode/:stopCode
```

Response
```
{
	"status":"SUCCESS",
    "data":[
              {
                  "stopCode":"51130",
                  "agencyName":"AC Transit",
                  "agencyCode":"AC",
                  "busNo":"232",
                  "arrivalTime":"2017-11-18T21:10:00Z"
               }
            ]
}
```
* gfl-client: It interacts with the external services like Slack etc. and returns the formatted response. (Default Port: 8884)

Request
```
GET /slack511
```

Response

![response to slack](https://github.com/payamrastogi/gfl-challenge/blob/master/elastic/screenshots/Screenshot%202017-11-18%2014.49.52.png "response")

* gfl-common: It contains the common resources used by other components

### References:

 * [Markdown Editor](https://jbt.github.io/markdown-editor/) for Markdown Editor
 * [Slack Slash Command](https://api.slack.com/slash-commands) Slash Commands Integration
 * [Open Feign Client](https://github.com/OpenFeign/feign) Open Feign Client for creating simple rest clients
 * [Spark Java](http://sparkjava.com) for creating simple rest resource to serve rest clients
 * [ngrok](https://ngrok.com) for creating tunnel to localhost (Testing slack client)

