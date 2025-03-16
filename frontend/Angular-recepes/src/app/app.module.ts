import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { RecettesListComponent } from './components/recettes-list/recettes-list.component';
import {HTTP_INTERCEPTORS, HttpClientModule}from'@angular/common/http';
import { RecetteService } from './services/recette.service';
import { FormsModule } from '@angular/forms';
import { OrderByPipe } from './order-by.pipe';
import { FilterPipe } from './filter.pipe';
import { NgxPaginationModule } from 'ngx-pagination';
import { RecetteDetailsComponent } from './components/recette-details/recette-details.component';
import { CommentaireComponent } from './components/commentaire/commentaire.component';
@NgModule({
  declarations: [
    AppComponent,
    RecettesListComponent,
    OrderByPipe,
    FilterPipe,
    RecetteDetailsComponent,
    CommentaireComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgxPaginationModule,
  ],
  providers: [RecetteService],
  bootstrap: [AppComponent]
})
export class AppModule { }
