const getAllAdmin = () => {
    // const baseUrl = window.location.href.split('/').slice(0, 4).join('/');
    // const url = `${baseUrl}/api/roles`;
    const url = `/api/roles`;
    fetch(url)
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
