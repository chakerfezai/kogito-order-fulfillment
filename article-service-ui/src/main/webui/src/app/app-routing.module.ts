import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {ProductPageComponent} from "./product-page/product-page.component";
import {ShoppingCartComponent} from "./shopping-cart/shopping-cart.component";
import {PaymentComponent} from "./payment/payment.component";

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'product-page/:name', component: ProductPageComponent},
  {path: 'cart', component: ShoppingCartComponent},
  {path: 'payment', component: PaymentComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
