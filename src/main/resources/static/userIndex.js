const authUserForm = document.querySelector('#tBody');
let res = '';
let user;

const authUser = (user) => {
  res = `
  <tr>
    <td>${user.id}</td>
    <td>${user.firstname}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${user.roles.map(role => " " + role.noPrefix)}</td>
  </tr> 
  `
  document.getElementById('currentUserEmail').textContent = `${user.email}`;
  document.getElementById('currentUserRoles').textContent = `${user.roles.map(role => " " + role.noPrefix)}`;

  authUserForm.innerHTML = res;
}

fetch('http://localhost:8080/api/authUser') 
  .then(response => response.json())
  .then(data => authUser(data))
  .catch(error => console.log(error));