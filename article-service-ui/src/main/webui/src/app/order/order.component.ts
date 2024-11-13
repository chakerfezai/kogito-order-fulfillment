import {AfterViewInit, Component, inject, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {Order} from "../_interface/order";

import {initFlowbite} from "flowbite";
import {Shipping} from "../_interface/shipping";
import {ShippingService} from "../service/shipping.service";
import {Observable,Subscription, timer} from "rxjs";
import { switchMap } from 'rxjs/operators';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent implements OnInit, AfterViewInit {

  orderService = inject(OrderService);
  shippingService = inject(ShippingService);
  orders = this.orderService.orders();
  everyFiveSeconds: Observable<number> = timer(0, 5000);
  subscription !: Subscription;

  ngOnInit(): void {
    setTimeout(() => {
      initFlowbite();
    }, 1000);
    this.subscription = timer(0, 5000).pipe(
      switchMap(() => this.orderService.allOrders())
    ).subscribe(result => {
      console.table(result)
      this.orders = result;
    }
    );
    this.fetchData();
  }

  fetchData() {
    this.orderService.allOrders().subscribe(value => {
      console.table(value)
      this.orders = value;
    });
  }

  ngAfterViewInit() {
    initFlowbite();
  }

  onShipping(order: Order) {
    let shipping: Shipping = {
      orderId: order.orderId,
      processInstanceId: order.processInstanceId,
      status: "COMPLETE"
    }
    this.shippingService.shippingActions(shipping).subscribe(value => {
      this.fetchData();
    });
  }

  onShippingFailed(order: Order) {
    let shipping: Shipping = {
      orderId: order.orderId,
      processInstanceId: order.processInstanceId,
      status: "FAILED"
    }
    this.shippingService.shippingActions(shipping).subscribe(value => {
      this.fetchData();
    });
  }

  onDelivred(order: Order) {
    console.log('hello', order);
  }
}
