Problem statement :

We have 2 systems that need to interact one with another through REST. A Client and a Server. In order to service the request, the Server needs to call Service A using JMS. Service A may need anywhere from 100ms to 15hours to reply also using JMS. The Server absolutely needs the reply from Service A before it can properly service the request from the Client.

Please come with a at least 1 possible Solution for this situation, ideally multiple possible Solutions, highlighting the cons and pros. Document your assumptions. Most likely anything beyond 3 scenarios doesn’t bring any additional value.

Please create diagrams and describe what is represented in the diagrams.

Solution :

There are three psossible solution to the above problem

Message Broker(JMS) - Message Broker can be used to communicate asynchronously from server to Service-A. Once the Server post the request to Service-A, Service-A can acknowledge in sychronous mode that Service-A has received the request and continue to process the request in the background. In a mean time Server will respond to Client with 200 status, so that client should not be waiting for actual response and should inform User that once the request will be processed you will get notified.
Once the processing is completed the Service-A can send back the response to JMS in Asynchronous mode, while Server should be listening to the Queue continuously to receive the response from Service-A. Finally, Server can feed the response to Client in asynchronous mode. Server Client communication can be done through WebSocket so that the data should be send back to user in asynchronous mode.

Pros :
Client can work in reactive mode. No Request timeout.

Cons : It adds a bit more complexity as need to consume and publish to JMS.

Rest CallBacks : Pass in the request a return URL that the server API can call to let the client know that work is completed.
As mentioned above the server can communicate to Service-A through JMS asysnchronously and once the work is completed Server will send back the data to the callback URL.
Pros : Server will take responsiblity to post the data back to Client. No need of external system to communicate back to Client like WebSocket Cons : Request may lost the track if the something goes wrong in Server while processing the reponse.

Post-Put Creation Pattern : In this pattern initial Post request will be made. if the initial request didn't make it to the server, a new PUT request can be initiated to send the data to Server and can be reissued with the URI until the client receives the data.
So when the resource was partially create with the POS and if the client gets timeout in between the client can send as many as PUT due to it's idempotency property.

Pros : no need to keep requet id of request as we do need to keep in Callbacks. Cons : Request thread would be processing same request again which may result in exahusting request thread

Decision :

I believe Message Broker with some asynchronous task runner is the much better way from realiablity perspective as message broker has a mechanism to reprocess the request in case if anthings goes wrong. Client can work in reactive mode as well as Request thread in the Server can serve other request as there is nothing to process or track until the work is completed and send back to Server from Service-A.
