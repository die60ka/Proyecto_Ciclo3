const login = () => {
    const username = document.getElementById("floatingInput").value;
    const password = document.getElementById("floatingPassword").value;
    const errorDiv = document.getElementById("error");

    if (username == "") {
        errorDiv.innerText = "Se requiere nombre de usuario";
        return;
    }
    if (password == "") {
        errorDiv.innerText = "Se requiere password"
        return;
    }

    const body = {
        "username": username,
        "password": password
    };
    postToLogin(body);

};

    const postToLogin = async (bodyObject) => {
        const url = "/api/login";
        const response = await fetch(url, {
            method: "POST",
            body: JSON.stringify(bodyObject),
            headers: {
                "Content-Type": "application/json"
            }
        });

        if(response.ok) {
            const user = await response.json();
            console.log(user.username);
            console.log(user.name);
        } else {
            document.getElementById("error").innerText = await response.text();
        }
};