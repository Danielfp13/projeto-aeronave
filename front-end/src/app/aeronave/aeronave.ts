export class Aeronave{
   id: number;
   nome: string;
   marca: string;
   ano: number;
   descricao: string;
   vendido: boolean;
   created: any;
   updated: any;

   constructor(nome: string, marca: string, ano: number, descricao: string, vendido: boolean){
      this.nome = nome;
      this.marca = marca;
      this.ano = ano;
      this.descricao = descricao;
      this.vendido = vendido;
  }
}