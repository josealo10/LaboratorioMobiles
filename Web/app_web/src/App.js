import React, { useState, useEffect } from 'react';
import logo from './logo.svg';
import './App.css';
import {Login} from './Components/Login/Login'
import { GeneralView } from './Components/GeneralView/GeneralView';


function App() {
  const [user, setUser] = React.useState({});

  useEffect(() => {
    console.log("user efect app component")
    setUser(localStorage.getItem('user'));
  },[])

  return (
    <div className="App" id="root">
      <header className="App-header">
      <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"/>
        <form id="form"/>
      {!user &&  <Login/> }
      {user && <GeneralView/>}
      </header>
    </div>
  );
}

export default App;
