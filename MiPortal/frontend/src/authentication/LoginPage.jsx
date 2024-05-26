import "primeflex/primeflex.css";
import "primereact/resources/themes/saga-blue/theme.css"; // import theme
import "primereact/resources/primereact.min.css"; // import prime components css
import "primeicons/primeicons.css"; // import icons
import React, { useState } from 'react';
import { Link } from 'react-router-dom';

import { InputText } from 'primereact/inputtext';
import { Checkbox } from 'primereact/checkbox';
import { Button } from 'primereact/button';

function LoginPage() {
     const [checked, setChecked] = useState(false); // State for Checkbox

   return (
       <>

           <div className="flex align-items-center justify-content-center">
               <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
                   <div className="text-center mb-5">
                       <img src="https://cdn-icons-png.flaticon.com/128/7062/7062990.png" alt="hyper" height={50} className="mb-3"/>
                       <div className="text-900 text-3xl font-medium mb-3">Bienvenido!</div>
                       <span className="text-600 font-medium line-height-3">Aún no tienes cuenta?</span>
                       <Link to={"/signup"} className="font-medium no-underline ml-2 text-blue-500 cursor-pointer">Unete ahora!</Link>
                   </div>

                   <div>
                       <label htmlFor="email" className="block text-900 font-medium mb-2">Usuario</label>
                       <InputText id="email" type="text" placeholder="Usuario" className="w-full mb-3"/>

                       <div className="flex align-items-center justify-content-between mb-6">
                           <div className="flex align-items-center">
                               <Checkbox id="rememberme" onChange={e => setChecked(e.checked)} checked={checked}
                                         className="mr-2"/>
                               <label htmlFor="rememberme">Remember me</label>
                           </div>
                           <a className="font-medium no-underline ml-2 text-blue-500 text-right cursor-pointer">Olvidaste
                               tu usuario?</a>
                       </div>

                       <Button label="Sign In" icon="pi pi-user" className="w-full"/>
                   </div>
               </div>
           </div>

       </>
   );
}

export default LoginPage


