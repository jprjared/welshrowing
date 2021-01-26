# Welsh Rowing (Team 1)

## Setup Instructions

```bash
# Clone the repository
git clone https://git.cardiff.ac.uk/c1741189/welshrowing_team1.git

# Go inside the directory
cd welshrowing_team1

# Build the server
./gradlew build
```

You can run a development server that runs with a H2 database and automatically loads test data, or a production-equivalent server that runs with MariaDB but does not load any test data.

**Runs dev/test server with H2 Database** (with test data)
```bash
java -jar welsh-rowing-0.0.1-SNAPSHOT.jar --spring.profiles.active=dev
```

**Runs prod server with MariaDB**
```bash
java -jar welsh-rowing-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

If you run the production server, please create a `WelshRowing` database first. The schema will automatically be created when you first run the production server.

You can optionally run the `security.sql` file (manually) from the `resources` folder to add DB security features for the Cyber Security module.

You can also optionally run the `data-h2.sql` file if you want to add test data to the production database, but this is not required.

Once the server is running, the website can be accessed from https://localhost:8443