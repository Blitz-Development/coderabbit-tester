# Getting Started with Java 21 Spring Boot Template

## What is This?

This is a **template repository** - a boilerplate starter project for building Spring Boot applications with Supabase integration. It's designed to be used as a starting point for new projects, not as a project you contribute to directly.

**Important:** This repository is read-only. You cannot and should not push changes to this template. Instead, you'll create your own copy to work with.

## How to Use This Template

### Use GitHub's Template Feature, Then Clone in IntelliJ

This creates your own repository first, then clones it:

1. **Click the "Use this template" button** on the GitHub repository page
   - You'll find this button at the top right of the repository page
   - Select "Create a new repository"

2. **Configure your new repository:**
   - Choose a name for your new repository (e.g., `my-awesome-app`)
   - Select the owner (your GitHub account or organization)
   - Choose visibility (public or private)
   - **Do NOT** initialize with README, .gitignore, or license (this template already includes them)

3. **Click "Create repository"** - GitHub will create a new repository with all the template code

4. **Clone in IntelliJ IDEA:**
   - Open IntelliJ IDEA
   - Select "Get from VCS"
   - Enter your new repository URL: `https://github.com/YOUR_USERNAME/YOUR_REPO_NAME.git`
   - Click "Clone"

## Next Steps After Creating Your Repository

Once you have your own copy of the template, follow these steps:

### Step 1: Rename the Application Class

The template includes a generic application class name that you should rename to match your project:

1. **Rename the main application class:**
   - Rename `Java21SpringbootTemplateApplication.java` to `<YourProjectName>Application.java`
   - Update the class name inside the file:
   ```java
   public class YourProjectNameApplication {
       public static void main(String[] args) {
           SpringApplication.run(YourProjectNameApplication.class, args);
       }
   }
   ```

2. **Rename the test class:**
   - Rename `Java21SpringbootTemplateApplicationTests.java` to `<YourProjectName>ApplicationTests.java`
   - Update the class name inside the file

3. **Update `pom.xml`:**
   - Change `<artifactId>` to your project name
   - Update `<name>` and `<description>` if desired

4. **Update `application.yaml`:**
   - Change `spring.application.name` to your project name

### Step 2: Configure Supabase

1. **Get your Supabase credentials:**
   - Sign up at [supabase.com](https://supabase.com) if you haven't already
   - Create a new project
   - Go to Project Settings → API to find your URL and anon key
   - Go to Project Settings → Database to find your database connection details

2. **Update `application.yaml`:**
   - Set `supabase.url` - Your Supabase project URL
   - Set `supabase.anon-key` - Your Supabase anonymous key
   - Configure database connection settings:
     - **Database URL format:**
       - For connection pooling (recommended): `jdbc:postgresql://aws-0-eu-central-1.pooler.supabase.com:6543/postgres`
       - For direct connection: `jdbc:postgresql://db.xxxxx.supabase.co:5432/postgres`
       - Find your connection string in Supabase Dashboard → Settings → Database
     - **Username format:** `postgres.bydefmabchistvvzdvun` (includes the project reference ID)
     - **Password:** Your database password (set when creating the Supabase project)
     - **Port:** `6543` for pooler, `5432` for direct connection

   You can set these as environment variables or directly in the `application.yaml` file. See the comments in `application.yaml` for format examples.

**Note:** Hibernate is configured with `ddl-auto: update`, which means it will automatically create and update database tables based on your entity classes. You don't need to manually create tables - just run the application and Hibernate will handle it!

### Step 3: Run the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Step 4: Test the HelloWorld Endpoint (Optional)

This template includes a `HelloWorldController` as a simple example to verify your API is working. This is a learning example and should be deleted once you start building your actual application.

**What it does:**
- Accepts a POST request with a JSON body containing a `name` field
- Returns a greeting message: "hi.. {name}"

**Test it with Postman:**
1. Open Postman (or any HTTP client)
2. Create a new POST request to: `http://localhost:8080/api/hello`
3. Set the Content-Type header to: `application/json`
4. Add this JSON body:
   ```json
   {
     "name": "YourName"
   }
   ```
5. Send the request - you should receive: `"hi.. YourName"`

**Test it from a frontend:**
```javascript
fetch('http://localhost:8080/api/hello', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json',
  },
  body: JSON.stringify({ name: 'YourName' })
})
  .then(response => response.text())
  .then(data => console.log(data)); // Output: "hi.. YourName"
```

**Note:** This controller is included as a learning example to help you understand:
- How to create REST controllers
- How to accept JSON request bodies using DTOs
- How to return responses

Once you understand how it works, you should delete `HelloWorldController.java` and `HelloWorldDto.java` and create your own controllers for your application.

## Understanding the Template Structure

This template includes:

- **HelloWorldController** - Example REST controller showing how to create API endpoints (should be deleted after learning)
- **SupabaseClient** - REST client for Supabase authentication (register/login)
- **UserProfile Entity** - Example entity showing how to link your data with Supabase Auth
- **SecurityConfig** - CORS configuration (allows all traffic for learning purposes)
- **Standard folder structure** - Controller, Service, Repository, Entity, DTO layers

See the [README.md](README.md) for more details about the project structure and features.

## Important Notes

- **This is a template** - Don't try to contribute changes back to this repository
- **Create your own repository** - Always work from your own copy
- **Security warning** - The SecurityConfig allows all traffic. This is intentional for learning, but you should secure it before production use
- **Package names** - You can keep the default package name or refactor it to match your organization

## Need Help?

- Check the [README.md](README.md) for more detailed information
- Review the inline code comments for guidance
- Consult the [Spring Boot documentation](https://spring.io/projects/spring-boot)
- Check the [Supabase documentation](https://supabase.com/docs)

## Contributing

**Do not contribute to this template repository.** This is a read-only template. If you have improvements or suggestions, please create an issue in your own fork or contact the template maintainers.

