//Here is the method to receive the data for the ToDo-List
public void sendTabletToDo(){
  OkHttpClient client = new OkHttpClient();

  Request request = new Request.Builder()
    .url("http://10.0.0.20:5000/sendTabletToDo")
    .get()
    .addHeader("cache-control", "no-cache")
    .addHeader("Postman-Token", "e37a2155-0284-465d-9463-f06984befc08")
    .build();

  Response response = client.newCall(request).execute();
}
//Here is the method to receive the data for the mirro
public void sendToTablet(){
  //Implementation of the OkHttpClient
  OkHttpClient client = new OkHttpClient();
  /*
  Here is the building part, here will be define on which url the client should send the message and is it a POST or a Get Request
  the headers is just to define in which language we speak.
  The Postman-Token is also in the header because I had test the request on the PostMan
  */
  Request request = new Request.Builder()
    .url("http://10.0.0.20:5000/sendToTablet")
    .get()
    .addHeader("cache-control", "no-cache")
    .addHeader("Postman-Token", "da228cf8-d525-4e51-a6fa-d87606179ae1")
    .build();
  // Here we execute the request hand get hopefully a success back
  Response response = client.newCall(request).execute();
}
// Here is the method that send the data for the mirror to the Raspberry
public void sendToRasp(String abmasse,String objekte,String positionen){
  //Implementation of the OkHttpClient to send as a Client
  OkHttpClient client = new OkHttpClient();
  //That the server will understand that we are sending a json we say it to it
  MediaType mediaType = MediaType.parse("application/json");
  //Here we are going to build our json, we build it dynamic and so the user can manipulate it with it parameters
  RequestBody body = RequestBody.create(mediaType, "{\n    \"postion\": \""+positionen+"\",\n    \"groese\": \""+abmasse+"\",\n    \"objekte\": \""+objekte+"\"\n}");
  /*
  Here is the building part, here will be define on which url the client should send the message and is it a POST or a Get Request
  the headers is just to define in which language we are speaking.
  The Postman-Token is also in the header because I had test the request on the PostMan
  */
  Request request = new Request.Builder()
    .url("http://10.0.0.20:5000/sendToRasp")
    .post(body)
    .addHeader("Content-Type", "application/json")
    .addHeader("cache-control", "no-cache")
    .addHeader("Postman-Token", "b2f7d1fd-0595-4f30-a1d0-542115ac83b5")
    .build();
  // Here we execute the request hand get hopefully a success back
  Response response = client.newCall(request).execute();
}
// Here is the method that send the data for the ToDo-List to the Raspberry
public void sendToDoRasp(String description, String status){
  OkHttpClient client = new OkHttpClient();

  MediaType mediaType = MediaType.parse("application/json");  
  RequestBody body = RequestBody.create(mediaType, "{\n\t\"description\" : \""+description+"\",\n\t\"status\" : \""+status+"\"\n}");
  Request request = new Request.Builder()
    .url("http://10.0.0.20:5000/sendTodo")
    .post(body)
    .addHeader("Content-Type", "application/json")
    .addHeader("cache-control", "no-cache")
    .addHeader("Postman-Token", "901b48da-edb8-41ca-ab2e-4a10b1d2b499")
    .build();

  Response response = client.newCall(request).execute();
}
// Here is the main method. The main will call all methods and send also the the right arguments 
public static void main(String[] args) {
  // These are the important variables 
  TextView abmasse,positionen,objekte, description, status;

      /*
        Here are the variable decleartion for the User Interface 
      */
        abmasse = findViewById(R.id.abmasse);
        positionen = findViewById(R.id.posistion);
        objekte = findViewById(R.id.Objekte);
        description = findViewById(R.id.description);
        status = findViewById(R.id.status);


        // The four different function we are providing 
        /*
        + The first function call a POST Request which send the whole informartion from the SmartPhone App to the Raspberry
        + The second function call a POST Request which send the a new ToDo List to the Raspberry
        + The third function call  a GET Request which receive the hole ToDo List from the Raspberry 
        + The fourth function call a GET Request which receive all the important data to generate the Mirro 
        */
        sendToRasp(abmasse.getText().toString(),positionen.getText().toString(),objekte.getText().toString());
        sendToDoRasp(description.getText().toString(),status.getText().toString());
        sendTabletToDo();
        sendToTablet();

}