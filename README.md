# Welsh Rowing (Team 1)

## Setup Instructions

```bash
# Clone the repository
git clone https://git.cardiff.ac.uk/c1741189/welshrowing_team1.git

# Go inside the directory
cd welshrowing_team1

# Build the server
./gradlew build (gradlew build)
or (gradlew assemble)
```
Please create a `welshrowing` database first. The schema will automatically be created when you first run the production server.

Then run this to execute the application and populatet tables
```bash
java -jar welsh-rowing-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

then run the `data-h2.sql` file if you want to add test data to the production database.

usernames: coach, athlete, applicant
password: pass

Once the server is running, the website can be accessed from https://localhost:8443 or http://localhost:8080




