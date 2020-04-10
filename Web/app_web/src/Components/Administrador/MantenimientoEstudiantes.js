import React, {useState, useEffect} from 'react';
import MaterialTable from 'material-table';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogContentText from '@material-ui/core/DialogContentText';
import DialogTitle from '@material-ui/core/DialogTitle';
import './MantenimientoEstudiantes.css'

export function MantenimientoEstudiantes(){

  var alumno
  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    console.log(alumno)
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  const handleConfirm = () => {
    console.log(alumno)
    /*
    var newData = {
      usuario:credencials.username,
      clave:credencials.password,
      cedula:alumno.cedula,
      nombre:alumno.nombre,
      telefono:alumno.telefono,
      email:alumno.email,
      carrera:alumno.carrera,
    }
    */
    //console.log(newData)
    //makeRequest(data,'POST')
    setOpen(false);
  }

  const [credencials, setCredencials] = React.useState({
    username: '',
    password: '',
})


const handleChange = pro => (e) =>{
  setCredencials({...credencials, [pro]: e.target.value})
  console.log(alumno)
}

    const [state, setState] = React.useState({
      columns: [
        { title: 'Cedula', field: 'cedula', type: 'numeric'},
        { title: 'Nombre', field: 'nombre' },
        { title: 'Telefono', field: 'telefono', type: 'numeric'},
        { title: 'Email', field: 'email'}
      ],
      data: [],
    });

    const [carreras, setCarreras] = React.useState({})

    function getEstudiantes(){
        fetch("http://localhost:8080/MobilesServer/ServeletAlumnos",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    setState({...state, ['data']: result.estudiantes})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    function getCarreras(){
        fetch("http://localhost:8080/MobilesServer/ServeletCarrera",{
            method: 'GET'
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    let obj = {}
                    result.carreras.forEach(element => {
                        obj[element.codigo] = element.nombre
                    });
                    let obj2 = {title: 'Carrera', field: 'carrera', lookup: obj}
                    let col = state.columns
                    col.push(obj2)
                    setState({...state, ['columns']:col})
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })
    }

    const makeRequest = (data,type) =>{
      if(type == 'PUT'){
          var url = 'http://localhost:8080/MobilesServer/ServeletAlumnos?action=PUT'
          var headers = {method: 'POST',body: JSON.stringify(data)}
      }
      if(type == 'DELETE'){
      var url = 'http://localhost:8080/MobilesServer/ServeletAlumnos?cedula=' + data.cedula + '&action=DELETE'
      var headers = {method: 'POST'}
      }
      if(type == 'POST'){
        var url = 'http://localhost:8080/MobilesServer/ServeletAlumnos'
        var headers = {method: type,body: JSON.stringify(data)}
      }
        fetch(url,headers)
            .then(res => res.json())
        .then(
            (result) => {
                if(result.success == false){
                    alert('Error: ' + result.error )
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
                setState(prevState => {
                  const data = [...prevState.data];
                  data.push(newData);
                  //makeRequest(data[data.length-1],'POST')
                  alumno = newData
                  console.log(alumno)
                  handleClickOpen()
                  return { ...prevState, data };
                });
              }, 600);
            }),
          onRowUpdate: (newData, oldData) =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                if (oldData) {
                  setState(prevState => {
                    const data = [...prevState.data];
                    data[data.indexOf(oldData)] = newData;
                    makeRequest(newData,'PUT')
                    return { ...prevState, data };
                  });
                }
              }, 600);
            }),
          onRowDelete: oldData =>
            new Promise(resolve => {
              setTimeout(() => {
                resolve();
                setState(prevState => {
                  const data = [...prevState.data];
                  makeRequest({cedula: data[data.indexOf(oldData)].cedula},'DELETE')
                  data.splice(data.indexOf(oldData), 1);
                  return { ...prevState, data };
                });
              }, 600);
            }),
        }}
      />
      <Dialog open={open} onClose={handleClose} aria-labelledby="form-dialog-title">
        <DialogTitle id="form-dialog-title">Credenciales</DialogTitle>
        <DialogContent>
          <TextField
            autoFocus
            margin="dense"
            id="username"
            label="Username"
            type="email"
            fullWidth
            onChange={handleChange('username')}
          />
          <TextField
            autoFocus
            margin="dense"
            id="password"
            label="Password"
            type="email"
            fullWidth
            onChange={handleChange('password')}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
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