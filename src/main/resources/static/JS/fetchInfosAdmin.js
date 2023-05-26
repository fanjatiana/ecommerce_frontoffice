fetch('/api/users')
    .then(response => response.json())
    .then(data => {
        const infosAdmin = data.filter(user => user.role.roleName === "Admin");
        return infosAdmin[0];
    })
    .catch(error => console.log(error));