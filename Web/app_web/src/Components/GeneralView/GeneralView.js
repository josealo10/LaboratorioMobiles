
import React, { useState, useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Drawer from '@material-ui/core/Drawer';
import List from '@material-ui/core/List';
import Divider from '@material-ui/core/Divider';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import InboxIcon from '@material-ui/icons/MoveToInbox';
import MailIcon from '@material-ui/icons/Mail';
import ExitToAppIcon from '@material-ui/icons/ExitToApp';
import PeopleIcon from '@material-ui/icons/People';
import SchoolIcon from '@material-ui/icons/School';

import './GeneralView.css'
import {MantenimientoCursos} from './../Administrador/MantenimientoCursos'
import {MantenimientoEstudiantes} from './../Administrador/MantenimientoEstudiantes'
import { CursosMatriculados } from '../Estudiante/CursosMatriculados';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
  },
  list: {
    width: 250,
  },
  fullList: {
    width: 'auto',
  },
}));

export function GeneralView(){
    const classes = useStyles();

    const [state, setState] = React.useState({
        left: false,
      });
    
      const toggleDrawer = (side, open) => event => {
        if (event.type === 'keydown' && (event.key === 'Tab' || event.key === 'Shift')) {
          return;
        }
    
        setState({ ...state, [side]: open });
      };
    
      const sideList = side => (
        <div
          className={classes.list}
          role="presentation"
          onClick={toggleDrawer(side, false)}
          onKeyDown={toggleDrawer(side, false)}
        >
          <List>
            {getSideMenuOption().map((text) => (
              getSideMenuListItems(text)
            ))}
          </List>
          <Divider />
          <List>
            {['Logout'].map((text) => (
              getSideMenuListItems(text)
            ))}
          </List>
        </div>
      );
      
      const [user, setUser] = React.useState({});
      const [content, setContent] = React.useState()

      function render(){document.getElementById("form").submit()}

      const logout = () => {
          localStorage.removeItem('user')
          render()
      }

      useEffect(() => {
        
        console.log("user efect general view")
        //if (!content && user.permiso == 'Administrador'){setContent('Mantenimiento de curos')}
        //if (!content && user.permiso == 'Alumno'){setContent('Cursos matriculados')}
        setUser(JSON.parse(localStorage.getItem('user')));
      },[])

      function getSideMenuOption(){
        if(user.permiso == 'Administrador'){return ['Mantenimiento de curos','Mantenimiento de estudiantes']}
        if(user.permiso == 'Alumno'){return ['Cursos matriculados']}
        return []
      }

      function getSideMenuListItems(pro){
        if (pro == 'Mantenimiento de curos'){
          return(
            <ListItem button key={pro} onClick={sideMenuHandler(pro)}>
                <ListItemIcon><SchoolIcon/></ListItemIcon>
                <ListItemText primary={pro} />
              </ListItem>
          )
        }
        if (pro == 'Mantenimiento de estudiantes'){
          return(
            <ListItem button key={pro} onClick={sideMenuHandler(pro)}>
                <ListItemIcon><PeopleIcon/></ListItemIcon>
                <ListItemText primary={pro} />
              </ListItem>
          )
        }
        if (pro == 'Cursos matriculados'){
          return (
            <ListItem button key={pro} onClick={sideMenuHandler(pro)}>
                <ListItemIcon><SchoolIcon/></ListItemIcon>
                <ListItemText primary={pro} />
              </ListItem>
          )
        }
        if (pro == 'Logout'){
          return(
            <ListItem button key={pro} onClick={sideMenuHandler(pro)}>
                <ListItemIcon><ExitToAppIcon/></ListItemIcon>
                <ListItemText primary={pro} />
              </ListItem>
          )
        }
      }

      const sideMenuHandler = pro => () =>{
        setContent(pro)
        if (pro == 'Logout'){
          logout()
        }
      }

    return (
      <div className="conteiner">
        <div>
        <AppBar position="static">
        <Toolbar >
            <IconButton onClick={toggleDrawer('left', true)} edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
            </IconButton>
            <Typography variant="h6" className={classes.title}>
              {user.permiso}
            </Typography>
        </Toolbar>
        </AppBar>
        <Drawer open={state.left} onClose={toggleDrawer('left', false)}>
          {sideList('left')}
        </Drawer>
        </div>
        <div className="content">
        {content == 'Mantenimiento de curos' && <MantenimientoCursos/>}
        {content == 'Mantenimiento de estudiantes' && <MantenimientoEstudiantes/>}
        {content == 'Cursos matriculados' && <CursosMatriculados/>}
        </div>
        </div>
      
    )
}