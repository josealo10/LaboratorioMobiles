using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Xamarin.Forms;
using Plugin.Toast;

namespace Juego_memoria
{
    // Learn more about making custom code visible in the Xamarin.Forms previewer
    // by visiting https://aka.ms/xamarinforms-previewer
    [DesignTimeVisible(false)]
    public partial class MainPage : ContentPage
    {
        private Controller Controller;
        private int X1, Y1, X2, Y2, Clicks;
        private Button Boton1, Boton2;

        public MainPage()
        {
            InitializeComponent();
            Controller = new Controller(new Model());
            Clicks = 0;
        }

        private void Button_Clicked(object sender, EventArgs e)
        {
            Button Boton = ((Button)sender);

            switch (Clicks)
            {
                case 0:
                    X1 = Boton.ClassId[0] - '0';
                    Y1 = Boton.ClassId[2] - '0';

                    Boton.Text = Controller.Model.Matriz[X1, Y1];
                    Boton.IsEnabled = false;

                    Boton1 = Boton;

                    Clicks += 1;
                    break;
                    
                case 1:
                    X2 = Boton.ClassId[0] - '0';
                    Y2 = Boton.ClassId[2] - '0';

                    Boton.Text = Controller.Model.Matriz[X2, Y2];
                    Boton.IsEnabled = false;

                    Boton2 = Boton;

                    if (Boton1.Text.Equals(Boton2.Text))
                    {
                        Boton1.BackgroundColor = Color.Yellow;
                        Boton2.BackgroundColor = Color.Yellow;

                        if (Ganador())
                        {
                            CrossToastPopUp.Current.ShowToastSuccess("Ganaste", Plugin.Toast.Abstractions.ToastLength.Long);
                            Reiniciar();
                        }
                    }

                    Clicks += 1;
                    break;

                case 2:
                    if (Boton1.BackgroundColor != Color.Yellow)
                    {
                        Boton1.IsEnabled = true;
                        Boton2.IsEnabled = true;

                        Boton1.Text = "";
                        Boton2.Text = "";
                    }

                    X1 = Boton.ClassId[0] - '0';
                    Y1 = Boton.ClassId[2] - '0';

                    Boton.Text = Controller.Model.Matriz[X1, Y1];
                    Boton.IsEnabled = false;

                    Boton1 = Boton;

                    Clicks = 1;
                    break;
            }
        }

        private bool Ganador()
        {
            foreach (Button Boton in Grid.Children)
            {
                if (Boton.BackgroundColor != Color.Yellow)
                {
                    return false;
                }
            }

            return true;
        }

        private void Reiniciar()
        {
            foreach (Button Boton in Grid.Children)
            {
                Boton.IsEnabled = true;
                Boton.Text = "";
                Boton.BackgroundColor = Color.Gray;
            }

            Controller.Reiniciar();
        }
    }
}
