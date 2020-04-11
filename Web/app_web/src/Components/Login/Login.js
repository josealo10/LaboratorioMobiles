import React from 'react';
import IconButton from '@material-ui/core/IconButton';
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from '@material-ui/core/InputLabel';
import InputAdornment from '@material-ui/core/InputAdornment';
import FormControl from '@material-ui/core/FormControl';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import AccountCircle from '@material-ui/icons/AccountCircle';
import Button from '@material-ui/core/Button';
import './Login.css';

export function Login(){

    const [values, setValues] = React.useState({
        username: '',
        password: '',
        showPassword: false,
    })


    const handleChange = pro => (e) =>{
        setValues({...values, [pro]: e.target.value})
    }

    const handleSubmit = () =>{
        var data = {username: values.username,clave: values.password}
        fetch("http://localhost:8080/MobilesServer/ServeletLogin",{
            method: 'POSt',
             body: JSON.stringify(data)
            })
        .then(res => res.json())
        .then(
            (result) => {
                if(result.success === true){
                    var obj = {username: values.username, clave: values.password, permiso: result.permiso}
                    if (obj.permiso === 'Alumno'){obj.cedula = result.cedula}
                    localStorage.setItem('user', JSON.stringify(obj))
                }else{
                    alert('Error: ' + result.error )
                }
            },
            (error) => {
                alert('Error: ' + error )
            })

    }

    const handleClickShowPassword = () => {
        setValues({ ...values, showPassword: !values.showPassword });
    };
    
    const handleMouseDownPassword = event => {
        event.preventDefault();
    }

    return (
        
        <div className = "loginContainer">
            <form >
            <div>Login</div>
            <div className='input'>
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
            <div className='input'>
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
            <div className='input'>
            <Button type="submit"  variant="contained" color="primary" onClick={handleSubmit}>
                        Login
            </Button>
            </div>
            </form>
      </div>
    )
        
}
