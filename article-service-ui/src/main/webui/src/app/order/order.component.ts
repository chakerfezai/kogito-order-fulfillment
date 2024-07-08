import {AfterViewInit, Component, inject, OnInit} from '@angular/core';
import {OrderService} from "../service/order.service";
import {Order} from "../_interface/order";

import {initFlowbite} from "flowbite";
import {Shipping} from "../_interface/shipping";
import {ShippingService} from "../service/shipping.service";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent implements OnInit, AfterViewInit {

  orderService = inject(OrderService);
  shippingService = inject(ShippingService);
  orders = this.orderService.orders();

  ngOnInit(): void {
    setTimeout(() => {
      initFlowbite();
    }, 1000);

    this.orderService.allOrders().subscribe(value => {
      console.table(value)
      this.orders = value;
      // setTimeout(() => {
      //   initFlowbite();
      // }, 1000);
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
    this.shippingService.shippingActions(shipping).subscribe(value => console.log(value));
  }

  onShippingFailed(order: Order) {
    let shipping: Shipping = {
      orderId: order.orderId,
      processInstanceId: order.processInstanceId,
      status: "FAILED"
    }
    this.shippingService.shippingActions(shipping).subscribe();
  }

  onDelivred(order: Order) {
    console.log('hello', order);
  }
}
