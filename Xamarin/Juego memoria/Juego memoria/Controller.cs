using System;
using System.Collections.Generic;
using System.Text;

namespace Juego_memoria
{
    class Controller
    {
        public Model Model { get; }

        public Controller(Model model)
        {
            Model = model;
        } 
        
        public void Reiniciar()
        {
            Model.Reiniciar();
        }
    }
}