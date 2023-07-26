# User_Expense_Tracker_Application
>## Framework Used 
 * SpringBoot
>## Database Used 
 * Swagger
 * mysql workbench
>## Language Used
* Java
>## For Querying use these
* Jpa Repository
* Custom Finder
* Custom Query
>## Data flow
In this project, we have four layers-
* **Controller** - The controller layer handles the HTTP requests, translates the JSON parameter to an object, authenticates the request, and transfers it to the business (service) layer. In short, it consists of views i.e., frontend part.
* **Service** -The business layer handles all the business logic. It consists of service classes and uses services provided by data access layers.
* **Repository** - This layer mainatains the h2-database thing on which CRUD operations are performed
* **Model** - This layer consists basically of the class level things- the various classes required for the project and these classes consist of the attributes to be stored.

>## Data Structure used in my project
*we created four entity classes, User and Product,Expence and authentication token  with the following attributes:
* **User**
    private Long id;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
* **Expense**
    private Long id;
    private String description;
    private LocalDate date;
    @ManyToOne
    private User user;
    @OneToMany
    private List<Product> productList;
* **Product**
    private Long id;
    private String productTitle;
    private String description;
    private Double productPrice;
* **Authentication token**
    private Long tokenId;
    private String token;
    private LocalDate tokenCreationDate;
     @OneToOne
    private User user ;
