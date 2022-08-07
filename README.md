# rekomind-api (deprecated)

This was the legacy rekomind API Gateway used in [rekomind](https://rekomind.com).

```
docker build --no-cache -t rekomind-api:1.2.0 .

docker run --name rekomind-api -it \
	-v /Users/tanis/Dev/rekomind/rekomind-api/src/src/main/resources/local/:/etc/rekomind-api \
	-e LOG4J2_CONFIG_FILE="/etc/rekomind-api/log4j2-local.xml" \
	-e REKOMIND_CORE_WS_ENDPOINT="rekomind-core-ws:8081" \
    	-e REKOMIND_RESOURCE_WS_ENDPOINT="rekomind-resource-ws:8082" \
	-p 8080:8080 \
	--rm rekomind-api:1.2.0
```
