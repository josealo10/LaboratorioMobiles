import React, {useState, useEffect} from 'react';
import IconButton from '@material-ui/core/IconButton';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import AccountCircle from '@material-ui/icons/AccountCircle';
import { createMuiTheme, withStyles, ThemeProvider } from '@material-ui/core/styles';
import Button from '@material-ui/core/Button';
import { green, purple } from '@material-ui/core/colors';
import './Login.css';

export function Login(){

    //const [nombre, setNombre]= React.useState('')
    //const [estadoNombre, setEstadoNombre] = React.useState('')

    const [values, setValues] = React.useState({
        username: '',
        password: '',
        showPassword: false,
    })


    const handleChange = pro => (e) =>{
        setValues({...values, [pro]: e.target.value})
    }


    /*
    const handleUser = (e) =>{
        setValues({...values, user: e.target.value})
    }

    
    const handleUser = (event) =>{
        setNombre(event.target.value)
    }

    const handlePassword = (event) =>{
        setPassword(event.target.value)
    }
    */
    const handleSubmit = () =>{
        var data = {username: values.username,clave: values.password}
        fetch("http://localhost:31762/Server/ServeletLogin",{
            method: 'POSt',
             body: JSON.stringify(data)
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success == true){
                    const obj = {username: values.username, clave: values.password, permiso: result.permiso}
                    console.log(obj)
                    localStorage.setItem('user', JSON.stringify(obj))
                    
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                console.log(error)
            })

    }

    const handleClickShowPassword = () => {
        setValues({ ...values, showPassword: !values.showPassword });
    };
    
    const handleMouseDownPassword = event => {
        event.preventDefault();
    }

    const ColorButton = withStyles(theme => ({
        root: {
          color: theme.palette.getContrastText(purple[500]),
          backgroundColor: green[500],
          '&:hover': { 
            backgroundColor: green[700],
          },
        },
      }))(Button);
      
      const theme = createMuiTheme({
        palette: {
          primary: green,
        },
      });

    return (
        
        <div class = "loginContainer">
            <form >
            <div>Login</div>
            <div class='input'>
            <FormControl variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Username</InputLabel>
                    <OutlinedInput
                        onChange={handleChange('username')}
                        endAdornment={
                            <InputAdornment position="end">
                              <AccountCircle />
                            </InputAdornment>
                          }
                        labelWidth={70}
                    />
                </FormControl>
            </div>
            <div class='input'>
                <FormControl variant="outlined">
                    <InputLabel htmlFor="outlined-adornment-password">Password</InputLabel>
                    <OutlinedInput
                        type={values.showPassword ? 'text' : 'password'}
                        onChange={handleChange('password')}
                        endAdornment={
                        <InputAdornment position="end">
                            <IconButton
                            aria-label="toggle password visibility"
                            onClick={handleClickShowPassword}
                            onMouseDown={handleMouseDownPassword}
                            edge="end"
                            >
                            {values.showPassword ? <Visibility /> : <VisibilityOff />}
                            </IconButton>
                        </InputAdornment>
                        }
                        labelWidth={70}
                    />
                </FormControl>
            </div>
            <div class='input'>
            <Button type="submit"  variant="contained" color="primary" onClick={handleSubmit}>
                        Login
            </Button>
            </div>
            </form>
      </div>
    )
        
}