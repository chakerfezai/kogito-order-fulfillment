import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {HomeComponent} from './home/home.component';
import {ProductPageComponent} from './product-page/product-page.component';
import {HttpClientModule} from "@angular/common/http";
import {ShoppingCartComponent} from './shopping-cart/shopping-cart.component';
import {PaymentComponent} from './payment/payment.component';
import {OrderComponent} from './order/order.component';
import {MatTableModule} from "@angular/material/table";
import {MatIcon, MatIconModule} from "@angular/material/icon";
import {MatMenu, MatMenuTrigger} from "@angular/material/menu";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatFormField} from "@angular/material/form-field";
import {MatOption, MatSelect} from "@angular/material/select";

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    ProductPageComponent,
    ShoppingCartComponent,
    PaymentComponent,
    OrderComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatIcon,
    MatMenuTrigger,
    MatMenu,
    MatIconModule,
    BrowserAnimationsModule,
    MatFormField,
    MatSelect,
    MatOption,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
