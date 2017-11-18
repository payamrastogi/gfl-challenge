# Readme.md

Installation:

 * git clone https://github.com/payamrastogi/gfl-challenge.git
 * cd gfl-challenge/elastic
 * modify config.properties in (gfl-elastic, gfl-client, gfl-sfbay, gfl-service)
 * rename install.txt to install.sh
 * rename ngrok.txt to ngrok (for testing on localhost)
 * run chmod +x install.sh
 * ./install.sh
 * run chmod +x ngrok
 * on different terminal execute command ./ngrok http [port_of_gfl-service]
 * copy the forwarding address and paste it in the slack slash command intgration setting's url field.


Description

The current folder contains 4 different projects

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



This is [on GitHub](https://github.com/jbt/markdown-editor) so let me know if I've b0rked it somewhere.


Props to Mr. Doob and his [code editor](http://mrdoob.com/projects/code-editor/), from which
the inspiration to this, and some handy implementation hints, came.

### Stuff used to make this:

 * [markdown-it](https://github.com/markdown-it/markdown-it) for Markdown parsing
 * [CodeMirror](http://codemirror.net/) for the awesome syntax-highlighted editor
 * [highlight.js](http://softwaremaniacs.org/soft/highlight/en/) for syntax highlighting in output code blocks
 * [js-deflate](https://github.com/dankogai/js-deflate) for gzipping of data to make it fit in URLs

