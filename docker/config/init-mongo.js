db.createUser(
    {
        user: "monitor-user",
        pwd: "monitor-password",
        roles: [
            {
                role: "readWrite",
                db: "monitor-db"
            }
        ]
    }
)