const url = 'http://localhost:8080/api/users';
const usersList = document.querySelector('#idBodyAllUsers');
const authUserForm = document.querySelector('#idBodyUser');
let result = '';

const  addUserForm = document.querySelector('.add-user-form');
const modalDeleteUser = new bootstrap.Modal(document.querySelector('#delete-modal'));
const formDeleteUser = document.querySelector('#delete-user-form');
const modalEditUser = new bootstrap.Modal(document.querySelector('#edit-modal'));
const formEditUser = document.querySelector('#edit-user-form')
const firstName = document.getElementById('Firstname');
const lastName = document.getElementById('Lastname');
const age = document.getElementById('Age');
const email = document.getElementById('Email');
const password = document.getElementById('Password');

const userList = (users) => {
  users.forEach(user => {
    result += `
      <tr>
        <td>${user.id}</td>
        <td>${user.firstname}</td>
        <td>${user.lastname}</td>
        <td>${user.age}</td>
        <td>${user.email}</td>
        <td>${user.roles.map(role => " " + role.noPrefix)}</td>
        <td  id="edit-user" class="text-center"><a class="btnEdit btn btn-info">Edit</a></td>
        <td  id="delete-user" class="text-center"><a class="btnDelete btn btn-danger">Delete</a></td>
      </tr>  
    `
  });
  usersList.innerHTML = result;
}

fetch(url)
  .then(response => response.json())
  .then(data => userList(data))
  .catch(error => console.log(error));

const authUser = (user) => {
  authUserForm.innerHTML = `
  <tr>
    <td>${user.id}</td>
    <td>${user.firstname}</td>
    <td>${user.lastname}</td>
    <td>${user.age}</td>
    <td>${user.email}</td>
    <td>${user.roles.map(role => " " + role.noPrefix)}</td>
  </tr> 
  `;
  document.getElementById('currentUserEmail').textContent = `${user.email}`;
  document.getElementById('currentUserRoles').textContent = `${user.roles.map(role => " " + role.noPrefix)}`
}

fetch('http://localhost:8080/api/authUser') 
  .then(response => response.json())
  .then(data => authUser(data))
  .catch(error => console.log(error));



const on = (element, event, selector, handler) => {
  element.addEventListener(event, e => {
    if(e.target.closest(selector)){
      handler(e);
    }
  })
} 
let idForm = 0;
let row = '';

on(document, 'click', '.btnDelete', e => {
  row = e.target.parentNode.parentNode;
  document.getElementById('delRole').innerHTML = '';
  idForm = row.children[0].innerHTML;
  const firstNameForm = row.children[1].innerHTML;
  const lastNameForm = row.children[2].innerHTML;
  const ageForm = row.children[3].innerHTML;
  const emailForm = row.children[4].innerHTML;
  const rolesStr = row.children[5].innerHTML.split(',');
  rolesStr.forEach(role => {
    let option = document.createElement('option');
    option.text = role.trim();
    let select = document.getElementById('delRole');
    select.appendChild(option);
  })
  delId.value = idForm;
  delFirstname.value = firstNameForm;
  delLastname.value = lastNameForm;
  delAge.value = ageForm;
  delEmail.value = emailForm;
  modalDeleteUser.show();
})

formDeleteUser.addEventListener('submit', e => {
  e.preventDefault();
  row.closest('tr').remove();
  fetch(url + '/' + idForm, {
    method: 'DELETE'
  })
})
let rowEdit = '';
on(document, 'click', '.btnEdit', e => {
  rowEdit = e.target.parentNode.parentNode;
  idForm = rowEdit.children[0].innerHTML;
  editId.value = idForm;
  editFirstname.value = rowEdit.children[1].innerHTML;
  editLastname.value = rowEdit.children[2].innerHTML;
  editAge.value = rowEdit.children[3].innerHTML;
  editEmail.value = rowEdit.children[4].innerHTML;
  editPassword.value = '';
  modalEditUser.show();
})

formEditUser.addEventListener('submit', e => {
  e.preventDefault();
  const select = document.getElementById('edit-user-role');
  const selected = [...select.options].filter(option => option.selected).map(option => option.value);
  let roles = [];
  selected.forEach(el => {
    let id = el === 'Admin' ? 1 : 2;
    roles.push({
      id: id,
      roleName: el
    });
  })
  fetch(`${url}/${idForm}`,{
    method: 'PATCH',
    headers: {
      'Content-Type':'application/json'
    },
    body: JSON.stringify({
      id: idForm,
      firstname: editFirstname.value,
      lastname: editLastname.value,
      age: editAge.value,
      email: editEmail.value,
      password: editPassword.value,
      roles: roles
    })  
  })
  .then()
  .then(response => response.json())
  .then(data => {
    rowEdit.children[1].innerHTML = data.firstname;
    rowEdit.children[2].innerHTML = data.lastname;
    rowEdit.children[3].innerHTML = data.age;
    rowEdit.children[4].innerHTML = data.email;
    rowEdit.children[5].innerHTML = data.roles.map(role => " " + role.noPrefix);
  })
  modalEditUser.hide();
})

addUserForm.addEventListener('submit', (e) => {
  e.preventDefault();
  const select = document.getElementById('Role');
  const selected = [...select.options].filter(option => option.selected).map(option => option.value);
  let roles = [];
  selected.forEach(el => {
    let id = el === 'ROLE_ADMIN' ? 1 : 2;
    roles.push({
      id: id,
      roleName: el
    });
  }) 
  fetch(url, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      firstname: firstName.value,
      lastname: lastName.value,
      age: age.value,
      email: email.value,
      password: password.value,
      roles: roles
    })
  })
  .then(res => res.json())
  .then(data => {
    const dataArr = []
    dataArr.push(data)
    userList(dataArr)
  })
  addUserForm.reset();
})




