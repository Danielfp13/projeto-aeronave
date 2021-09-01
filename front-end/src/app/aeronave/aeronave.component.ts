import { Component, OnInit } from '@angular/core';
import { AeronaveService } from '../aeronave.service';
import { Aeronave } from './aeronave';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SomatorioDecada } from './somatorioDecada';

@Component({
  selector: 'app-aeronave',
  templateUrl: './aeronave.component.html',
  styleUrls: ['./aeronave.component.css']
})
export class AeronaveComponent implements OnInit {
  formulario : FormGroup
  somatorioDecadas: SomatorioDecada[] = [];
  
  constructor(private service: AeronaveService, private fb: FormBuilder) { 
    
  }

  ngOnInit(): void {

    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      marca:['',Validators.required],
      ano:['',Validators.required],
      vendido:['',Validators.required],
      descricao:['']
    })
    this.findSomatorioDecada()
  }

  onSubmit(){

    const formValues = this.formulario.value;
    const aeronave: Aeronave = new Aeronave(formValues.nome, formValues.marca, formValues.ano, formValues.descricao, formValues.vendido);
    console.log(this.formulario.value)
    console.log(aeronave)
    this.service.save(aeronave).subscribe( resposta => {
      this.formulario.reset();
      this.formulario.controls.nome.markAsUntouched
      this.formulario.controls.marca.markAsUntouched
      this.formulario.controls.ano.markAsUntouched
      this.formulario.controls.descricao.markAsUntouched
      this.formulario.controls.vendido.markAsUntouched
    console.log(resposta)
    }, responseError=>{
      console.log(responseError)
    })
   this.findSomatorioDecada()
  }

  findSomatorioDecada(){
    this.service.findDecada().subscribe( resposta => {
      this.somatorioDecadas = resposta
      console.log(this.somatorioDecadas);
    })
  }
}
