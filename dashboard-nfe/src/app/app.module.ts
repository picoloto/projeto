import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { ToolbarComponent } from './core/toolbar/toolbar.component';
import { HttpClientModule } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';

import { TratamentoErrosService } from './core/http/tratamento-erros.service';
import { MessageService } from 'primeng/components/common/messageservice';
import { ToastModule } from 'primeng/toast';

@NgModule({
  declarations: [
    AppComponent,
    ToolbarComponent,
  ],
  imports: [
    BrowserModule,
    CommonModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ButtonModule,
    ToastModule
  ],
  providers: [TratamentoErrosService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
