import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { fromEvent } from 'rxjs';
import { debounceTime } from 'rxjs/operators';
import { AppService } from './app.component.service';

declare var $: any;

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  @ViewChild('entrada', { static: true }) entrada: ElementRef;
  @ViewChild('snackbar', { static: true }) snackbar: ElementRef;
  public title = "Cadastro";
  public pesquisar = true;
  public mensagem = '';
  public error = false;
  public idExcluir: number;
  public funcionario: any = {};
  public funcionarios: Array<any> = [];

  constructor(
    private appService: AppService
  ) { }


  ngOnInit() {
  }

  ngAfterViewInit() {
    this.getFuncionarios();
    setTimeout(() => {
      //Responsável pelo pesquisar
      try {
        fromEvent(document.getElementById('entrada'), 'keyup')
          .pipe(debounceTime(1000))
          .subscribe(valor => {
            var string = valor['path'][0]['value'];

            if (string !== '') {

              for (let i = 0; i < this.funcionarios.length; i++) {
                const funcionario = this.funcionarios[i];
                if (funcionario.nome.toUpperCase().includes(string.toUpperCase())) {
                } else {
                  this.funcionarios.splice(i, 1);
                  i--;
                }
              }
            } else {
              this.getFuncionarios();
            }
          });
      } catch (error) {
        console.log("AQUI");

      }
    }, 1000);
  }

  getFuncionarios() {
    this.appService.buscar().then(response => {
      if (response.status == 'Sucesso') {
        this.funcionarios = response.data || [];
      }
    })

  }

  salvar(form: any): void {
    clearTimeout();
    if (form.valid) {
      this.appService.salvar(this.funcionario).then(response => {
        if (response.status == 'Sucesso') {
          if (!this.funcionario.id) {
            this.mensagem = "Salvo com sucesso."
          } else {
            this.mensagem = "Editado com sucesso.";
            this.editado();
          }

          this.snackbar.nativeElement.className = "show btn-success";
          this.error = false;
          this.funcionario = {};
        }
      });
    } else {
      this.mensagem = "Campos obrigatórios não informados.";
      this.snackbar.nativeElement.className = "show btn-danger";
      this.error = true;
    };

    setTimeout(function () {
      this.snackbar.className = this.snackbar.className.replace("show", "");
    }, 2500);

  }

  editar(funcionario): void {
    this.snackbar.nativeElement.className = this.snackbar.nativeElement.className.replace("show", "");
    this.pesquisar = false;
    this.title = "Edição";
    this.funcionario = funcionario;
  }

  adicionar(): void {
    this.snackbar.nativeElement.className = this.snackbar.nativeElement.className.replace("show", "");
    this.pesquisar = false;
    this.title = "Cadastro";
    this.mensagem = '';
  }

  excluirValidacao(id): void {
    this.idExcluir = id;
    $('#deletar').modal('show');
  }

  excluir(): void {
    clearTimeout();

    this.appService.deletar(this.idExcluir).then(response => {
      if (response.status == 'Sucesso') {
        this.mensagem = "Deletado com sucesso.";
        this.snackbar.nativeElement.className = "show btn-success";
      }
    }).then(() => {
      $('#deletar').modal('hide');
      this.getFuncionarios();
      this.idExcluir = null;
    });

    setTimeout(function () {
      this.snackbar.className = this.snackbar.className.replace("show", "");
    }, 3000);
  }

  editado(): void {
    this.funcionario = {};
    this.pesquisar = true;
    this.ngAfterViewInit();
  }

  voltar(): void {
    this.funcionario = {};
    this.pesquisar = true;
    this.snackbar.nativeElement.className = this.snackbar.nativeElement.className.replace("show", "");
    this.ngAfterViewInit();
  }

}
