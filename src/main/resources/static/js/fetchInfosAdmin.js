const getAllAdmin = () => {
    fetch('/api/roles')
        .then(response => response.json())
        .then(data => {
            console.log(data)
            const selectElement = document.getElementById("adminName");
            data.forEach(element => {
                selectElement.innerHTML += `
                <option value="${element.fullName}">${element.fullName}</option>
           `
            })
        })
        .catch(error => {
            console.error('Une erreur s\'est produite:', error);
        });
}

getAllAdmin();
