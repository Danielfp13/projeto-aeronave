import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AeronaveListaComponent } from './aeronave-lista/aeronave-lista.component';
import { Aeronave } from './aeronave/aeronave';
import { AeronaveComponent } from './aeronave/aeronave.component';

const routes: Routes = [
  { path: 'form', component: AeronaveComponent },
  { path: 'form/:id', component: AeronaveComponent },
  { path: 'lista', component: AeronaveListaComponent },
  { path: '', redirectTo: '/lista', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
