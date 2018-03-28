Beispiel um grob verschiedene Möglichkeiten von JSON Komrimierung zu bewerten. Dabei wurde das JSON in zwei verschiedenen Varianten modelliert:

```
exampleLatLongWithAttribs.json: 

{
	"data": [
		{
			"latitude": "32.23717",
			"longitude": "-154.606802"
		},
		{
			"latitude": "74.376992",
			"longitude": "104.675673"
		},
		...
	],
	"page": {
		"size": 100,
		"totalElements": 100,
		"totalPages": 1,
		"number": 100
	}
}


JSON        	7108
JSON + GZIP 	2657
BSON        	5968
BSON + GZIP 	3274
```

````
exampleLatLongAsArray.json: 

{
	"data": [
		[
			"-54.738255",
			"78.867729"
		],
		[
			"23.16077",
			"87.561024"
		],
		...
	],
	"page": {
		"size": 100,
		"totalElements": 100,
		"totalPages": 1,
		"number": 100
	}
}


JSON        	4602
JSON + GZIP 	2501
BSON        	4462
BSON + GZIP 	3172
```

