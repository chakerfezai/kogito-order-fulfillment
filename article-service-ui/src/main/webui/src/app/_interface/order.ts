import {OrderItem} from "./order-item";
import {OrderStatus} from "./enum/order.status";

export interface Order {
  orderId?: number
  customerId?: number;
  orderItems?: OrderItem[];
  status?: OrderStatus;
  orderDate?: Date;
  reference?:String;
  totalAmount?:number;
  countryOrigin?: string;
  countryDestination?: string;
  shippingAddress?:string;
  processInstanceId?:string;
}

