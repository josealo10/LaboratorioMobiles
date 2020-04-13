using System;
using System.Collections.Generic;

namespace Juego_memoria
{
    class Model
    {
        public string[,] Matriz { get; }
        private List<string> Abecedario;
        private string[] Letras = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };
        private Random Aleatorio;

        public Model()
        {
            Matriz = new string[4, 5];
            Abecedario = new List<string>();
            Aleatorio = new Random();
            LlenarAbecedario();
            LlenarMatriz();
        }

        private void LlenarAbecedario()
        {
            foreach (string s in Letras)
            {
                Abecedario.Add(s);
            }
        }
        
        private void LlenarMatriz()
        {
            for (int i = 0; i < 4; ++i)
            {
                for (int j = 0; j < 5; ++j)
                {
                    int posicion = Aleatorio.Next(0, Abecedario.Count);

                    Matriz[i, j] = Abecedario[posicion];

                    Abecedario.RemoveAt(posicion);
                }
            }
        }

        public void Reiniciar()
        {
            Abecedario.Clear();

            LlenarAbecedario();
            LlenarMatriz();
        }
    }
}
