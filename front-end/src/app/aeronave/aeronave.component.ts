import { Component, OnInit } from '@angular/core';
import { AeronaveService } from '../service/aeronave.service';
import { Aeronave } from './aeronave';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { SomatorioDecada } from './somatorioDecada';
import { SomatorioMarca } from './somatorioMarca';
import { SomatorioSemana } from './somatorioSemana';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-aeronave',
  templateUrl: './aeronave.component.html',
  styleUrls: ['./aeronave.component.css']
})
export class AeronaveComponent implements OnInit {
  formulario: FormGroup
  somatorioDecadas: SomatorioDecada[] = [];
  somatorioMarcas: SomatorioMarca[] = [];
  somatorioSemanas: SomatorioSemana[] = [];
  aeronave: Aeronave;
  aeronaveId: any;

  constructor(private service: AeronaveService,
    private fb: FormBuilder,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private snackBar: MatSnackBar
  ) {
    this.aeronave = new Aeronave('', '', 0, '', false);
  }


  ngOnInit(): void {

    this.montarFormulario()

    let params: Observable<Params> = this.activatedRoute.params;
    params.subscribe(urlParams => {
      this.aeronaveId = urlParams['id'];
      if (this.aeronaveId) {
        this.service.getAeronaveById(this.aeronaveId).subscribe(
          (response) => {
            this.aeronave = response;
            this.montarFormularioAltear();
          },
          (errorResponse) => {
            console.log(errorResponse)
          }
        );
      }
    });
    this.atualizarRelatorio()
  }



  onSubmit() {
    if (this.aeronaveId) {
      const formValues = this.formulario.value;
      this.aeronave = new Aeronave(formValues.nome, formValues.marca, formValues.ano, formValues.descricao, formValues.vendido);
      this.service.alterarAeronave(this.aeronave, this.aeronaveId).subscribe(resposta => {
        this.snackBar.open('Aeronave atualizada com sucesso.', 'Sucesso', {
          duration: 2000
        })
        this.atualizarRelatorio();
        this.router.navigate(['/lista']);
      }, responseError => {
        this.snackBar.open('Erro ao atualizar aeronave.', 'Erro', {
          duration: 2000
        })
      })
    } else {
      const formValues = this.formulario.value;
      this.aeronave = new Aeronave(formValues.nome, formValues.marca, formValues.ano, formValues.descricao, formValues.vendido);
      this.service.save(this.aeronave).subscribe(resposta => {
        this.snackBar.open('Aeronave adicionada com sucesso.', 'Sucesso', {
          duration: 2000
        })
        this.atualizarRelatorio();
        this.router.navigate(['/lista']);
      }, responseError => {
        this.snackBar.open('Erro ao adicionar aeronave.', 'Erro', {
          duration: 2000
        })
      })
    }
  }

  montarFormulario(){
    this.formulario = this.fb.group({
      nome: ['', Validators.required],
      marca: ['', Validators.required],
      ano: ['', [Validators.required, Validators.minLength(0), Validators.maxLength(2100)]],
      vendido: ['', Validators.required],
      descricao: ['']
    })
  }

  findSomatorioDecada() {
    this.service.findDecada().subscribe(resposta => {
      this.somatorioDecadas = resposta
    })
  }

  findSomatorioMarca() {
    this.service.findMarca().subscribe(resposta => {
      this.somatorioMarcas = resposta
      console.log(resposta)
    })
  }

  findSomatorioSemana() {
    this.service.findSemana().subscribe(resposta => {
      this.somatorioSemanas = resposta
      console.log(resposta)
    })
  }

  voltarParaListagem() {
    this.router.navigate(['/lista']);
  }

  resetarFormulario() {
    this.formulario.reset();
    this.formulario.controls.nome.markAsUntouched
    this.formulario.controls.marca.markAsUntouched
    this.formulario.controls.ano.markAsUntouched
    this.formulario.controls.descricao.markAsUntouched
    this.formulario.controls.vendido.markAsUntouched
    this.atualizarRelatorio()
  }

  montarFormularioAltear(){
    this.formulario.controls.nome.setValue(this.aeronave.nome)
    this.formulario.controls.marca.setValue(this.aeronave.marca)
    this.formulario.controls.ano.setValue(this.aeronave.ano)
    this.formulario.controls.descricao.setValue(this.aeronave.descricao)
    this.formulario.controls.vendido.setValue(this.aeronave.vendido)
  }

  atualizarRelatorio(){
    this.findSomatorioDecada()
    this.findSomatorioMarca()
    this.findSomatorioSemana()
  }

}
