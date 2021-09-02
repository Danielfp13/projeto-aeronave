import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { AeronaveService } from '../service/aeronave.service';
import { Aeronave } from '../aeronave/aeronave';
import { DialogExcluirComponent } from '../dialog-excluir/dialog-excluir.component';

@Component({
  selector: 'app-aeronave-lista',
  templateUrl: './aeronave-lista.component.html',
  styleUrls: ['./aeronave-lista.component.css']
})
export class AeronaveListaComponent implements OnInit {

  menssagemDeResposta: string
  aeronaves: Aeronave[] = [];
  busca: string = ""

  page: number = 0;
  linePerPage: number = 8;
  direction: string = 'ASC';
  orderBy: string = 'id';
  totalElementos: number = 0;
  totalPage: number = 0;
  pageSizeOptions: number[] = [8]

  colunas = ['id', 'nome', 'marca', 'ano', 'descricao', 'vendido', 'alterar'];

  constructor(
    private service: AeronaveService,
    private router: Router,
    public dialog: MatDialog,
    private snackBar: MatSnackBar
  ) {

  }

  ngOnInit(): void {
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca);
  }

  findPage(page: number, linePerPage: number, direction: string, orderBy: string, id: string, marca: string) {
    this.service.findPage(page, linePerPage, direction, orderBy, id, marca).subscribe(
      response => {
        this.aeronaves = response.content
        this.totalElementos = response.totalElements
        this.totalPage = response.totalPages
        this.page = response.number
      }, responseError => {

      }
    );
  }

  paginar(event: PageEvent) {
    this.page = event.pageIndex
    this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca);
  }

  novoCadastro() {
    this.router.navigate(['/form'])
  }

  openDialog(aeronave: Aeronave) {
    let dialogRef = this.dialog.open(DialogExcluirComponent, { data: { Aeronave: aeronave } });
    dialogRef.afterClosed().subscribe(response => {
      if (response === "true") {
        this.deletar(aeronave)
      }
    })
  }

  deletar(aeronave: Aeronave) {
    this.service.deleteById(aeronave.id).subscribe(
      (response) => {
        this.snackBar.open('Aeronave excluida com sucesso.', 'Sucesso', {
          duration: 3000
        })
        this.findPage(this.page, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca);
      }, (errorResponse) => {
        this.snackBar.open('Erro ao excluir aeronave.', 'Erro', {
          duration: 3000
        })
      }
    )
  }
  buscar() {
    this.findPage(0, this.linePerPage, this.direction, this.orderBy, this.busca, this.busca);
  }
}

