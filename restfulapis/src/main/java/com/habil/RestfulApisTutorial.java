package com.habil;

public class RestfulApisTutorial
{

}

// Restful api (representational state transfer) is a web service that  follows REST princoples:
// Stateless - each request contains all the necessary info.
// Resource-based - each entity is treated as a resource.
// Uses HTTP methods to perform actions on resources.

// resource - a data object (user, product). 
// endpoint - the url path used to access and manipulate the resource. 

// Spring Boot 

// @RestController 
// @RequestMapping("/api/users") 
// public class UserController
// {
//     @GetMapping
//     public List<User> getAllUsers() // GET /api/users
//     {}
//     @GetMapping("/{id}")
//     public User getUser(@PathVariable Long id) // path variable: GET /api/users/{id}
//     {}
//     @GetMapping("/search")
//     public List<User> search(@RequestParam String name) // Query Parameters - GET /api/users/search?name=doe
//     {}
//     @PostMapping
//     public User createUser(@RequestBody User user) // POST /api/users
//     {}
// }

// Parameters 
// Path parameters 
// Query parameters 
// Header Parameters - Authorization, Accept
// Body Parameter - JSON/XML in request body 

// Headers 
// Content-Type: application/json, multipart/form-data, application/x-www-form-urlencoded, ... 
// Authorization: Bearer <token> 

// @PostMapping
// public ResponseEntity<String> createUser (
//     @RequestBody User user,
//     @RequestHeader("Authorization") String token
// )
// {
//     return ResponseEntity.ok("User created"); 
// }

// using aplication/x-www-form-urlencoded 
// @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
// public String login(@RequestParam String username, @RequestParam String password)
// {
//     return "Username: " + username + ", Password: " + password;
// }
// OR 
// @PostMapping (value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
// public String login (LoginRequest loginRequest)
// {
//     return "Hello, " + loginRequest.getUsername();
// }
// public static class LoginRequest 
// {
//     private String username;
//     private String password;
//     // getters and setters 
// }
// OR 
// @ModelAttribute alternative to @RequestParam 
// @PostMapping("/login")
// public String login(@ModelAttribute LoginRequest loginRequest)
// {
//     return "Logged in as " + loginRequest.getUsername();
// }

// HTTP methods
// GET - retirive resource
// POST - create new resource
// PUT - replace resource
// PATCH - partial update 
// DELETE - remove resource 
// OPTIONS - list supported ops 

// Request / Response Bodies (JSON/XML) 
// {
//     "id": 1,
//     "name": "John Doe"
// }
// <User>
//     <id>1</id>
//     <name>John Doe</name>
// </User>
// @PostMapping("/user") // SpringBoot handles both using Jackson(JSON) or JAXB(XML)
// public ResponseEntity<User> addUser(@RequestBody User user)
// {
//     return ResponseEntity.ok(user);
// }

// Form-Data (Multipart) 
// @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
// public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
// {
//     return ResponseEntity.ok("File uploaded: " + file.getOriginalFilename());
// }

// Status Codes 
// 200 - OK 
// 201 - Created 
// 204 - No Content 
// 400 - Bad Request 
// 401 - Unauthorized 
// 403 - Forbidden 
// 404 - Not Found 
// 500 - Internal Server Error 

// return new ResponseEntity<>(HttpStatus.NOT_FOUND); 


// Using an Embedded Server: Undertow 
// it is a lightweight performant java web server (used by WildFly, Red Hat) 

// import io.undertow.Undertow;
// import io.undertow.util.Headers;

// public class UndertowServer
// {
//     public static void main(String[] args)
//     {
//         Undertow server = Undertow.builder()
//         .addHttpListener(8080, "localhost")
//         .setHandler(exchange -> 
//         {
//             exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "text/plain");
//             exchange.getResponseSender().send("Hello from Undertow!");

//             // exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
//             // exchange.getResponseSender().send("{\"message\": \"Hello JSON\"}");
//             // undertow is lowerlevel than springboot. for restapis, it's often paired with frameworks
//             // like JAX-RS, Quarkus, or custom JSON handling 
//         })
//         .build();

//         server.start();
//     }
// }


// HttpServlet 
// this requires: 
// Java SE >= 8
// Servlet API 
// Apache Tomcat 
// JSON handling (org.json, Gson, ...)

// Project Structure 
// RestApiWithoutFramework/
// ├── src/
// │   └── com/example/api/
// │       └── UserServlet.java
// ├── WebContent/
// │   └── WEB-INF/
// │       └── web.xml


// UserServlet.java 
// public class UserServlet extends HttpServlet
// {
//     // GET /api/user?id=1
//     @Override
//     protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//     {
//         String id = req.getParameter("id");
//         resp.setContentType("application/json");
//         PrintWriter out = resp.getWriter();
//         if (id != null && id.equals("1"))
//         {
//             out.println("{ \"id\": 1, \"name\": \"Jane Doe\" }");
//         }
//         else
//         {
//             resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
//             out.println("{ \"error\": \"User not found!\" }");
//         }
//     }

//     // POST /api/user 
//     @Override
//     protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
//     {
//         String name = req.getParameter("name");

//         resp.setContentType("application/json");
//         PrintWriter out = resp.getWriter();

//         if (name != null)
//         {
//             out.println("{ \"message\": \"User created\", \"name\": \"" + name +"\"}");
//         }
//         else
//         {
//             resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//             out.println("{ \"error\": \"Missing name parameter\" }");

//         }
//     }
// }