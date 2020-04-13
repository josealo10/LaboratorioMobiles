import React, { useEffect } from 'react';
import MaterialTable from 'material-table';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import './MantenimientoEstudiantes.css'

export function MantenimientoEstudiantes(){

  const [alumno,setAlumno] = React.useState({})
  const [open, setOpen] = React.useState(false);
  const [credencials, setCredencials] = React.useState({
    username: '',
    password: '',
})

const [state, setState] = React.useState({
  columns: [
    { title: 'Cedula', field: 'cedula', type: 'numeric'},
    { title: 'Nombre', field: 'nombre' },
    { title: 'Telefono', field: 'telefono', type: 'numeric'},
    { title: 'Email', field: 'email'}
  ],
  data: [],
});
  const handleConfirm = () => {
    alumno.usuario = credencials.username
    alumno.clave = credencials.password
    makeRequest(alumno,'POST')
    setOpen(false);
  }
    function getEstudiantes(){
        fetch("http://localhost:8080/Server/ServeletAlumnos",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success === true){
                    setState({...state, data: result.estudiantes})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    function getCarreras(){
        fetch("http://localhost:8080/Server/ServeletCarrera",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success === true){
                    let obj = {}
                    result.carreras.forEach(element => {
                        obj[element.codigo] = element.nombre
                    });
                    let obj2 = {title: 'Carrera', field: 'carrera', lookup: obj}
                    let col = state.columns
                    col.push(obj2)
                    setState({...state, columns:col})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    const makeRequest = (data,type) =>{
      var url,headers
      if(type === 'PUT'){
           url = 'http://localhost:8080/Server/ServeletAlumnos?action=PUT'
           headers = {method: 'POST',body: JSON.stringify(data)}
      }
      if(type === 'DELETE'){
       url = 'http://localhost:8080/Server/ServeletAlumnos?cedula=' + data.cedula + '&action=DELETE'
       headers = {method: 'POST'}
      }
      if(type === 'POST'){
         url = 'http://localhost:8080/Server/ServeletAlumnos'
         headers = {method: type,body: JSON.stringify(data)}
      }
        fetch(url,headers)
            .then(res => res.json())
        .then(
            (result) => {
                if(result.success === false){
                    alert('Error: ' + result.error )
                }else{
                  getEstudiantes()
                  setAlumno({})
                }
            },
            (error) => {
                console.log(error)
            })
        setState(state)
        
    }

    useEffect(() => {
        getCarreras()
        getEstudiantes()
      },[])
  
    return (
        <div>
      <MaterialTable
        title="Estudiantes"
        columns={state.columns}
        data={state.data}
        editable={{
          onRowAdd: newData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                setAlumno(newData)
                setOpen(true)
              }, 600);
            }),
          onRowUpdate: (newData, oldData) =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  makeRequest(newData,'PUT')
                }
              }, 600);
            }),
          onRowDelete: oldData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  makeRequest(oldData,'DELETE')
                }
              }, 600);
            }),
        }}
      />
      <Dialog open={open} onClose={e => setOpen(false)} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Credenciales</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="username"
            label="Username"
            type="email"
            fullWidth
            onChange={e => setCredencials({...credencials, username: e.target.value})}
          />
          <TextField
            autoFocus
            margin="dense"
            id="password"
            label="Password"
            type="email"
            fullWidth
            onChange={e => setCredencials({...credencials, password: e.target.value})}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={e => setOpen(false)} color="primary">
            Cancelar
          </Button>
          <Button onClick={handleConfirm} color="primary">
            Confirmar
          </Button>
        </DialogActions>
      </Dialog>
      </div>
    );
  }