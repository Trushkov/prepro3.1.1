window.onload = function () {
    `use strict`
    let str = "";
    fetch("/user_data").then(
        res => {
            res.json().then(
                data => {
                    let role = "";
                    $.each(data.roles, function (index, value) {
                        role += value.name + " ";
                    })
                    str = `<strong>${data.login}</strong> with roles: ${role}`;
                    document.getElementById("head").innerHTML = str;
                })
        })

    fetch("/user_data").then(
        res => {
            res.json().then(
                data => {
                    let role = "";
                    $.each(data.roles, function (index, value) {
                        role += value.name + " ";
                    })
                    console.log(data);
                    let table = `<tr><td>${data.id}</td>
                                     <td>${data.firstName}</td>
                                     <td>${data.lastName}</td>
                                     <td>${data.age}</td>
                                     <td>${data.login}</td>
                                     <td>${role}</td>
                                 </tr>`
                    document.getElementById("data").innerHTML = table;
                }
            )
        }
    )
}