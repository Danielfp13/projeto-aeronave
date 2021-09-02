import { Component, OnInit, Inject} from '@angular/core';
import { Aeronave } from '../aeronave/aeronave';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-dialog-excluir',
  templateUrl: './dialog-excluir.component.html',
  styleUrls: ['./dialog-excluir.component.css']
})

export class DialogExcluirComponent implements OnInit {

  aeronave: Aeronave 
  constructor( @Inject (MAT_DIALOG_DATA) public data:any) { }

  ngOnInit(): void {
    this.aeronave = this.data.Aeronave
  }

}
