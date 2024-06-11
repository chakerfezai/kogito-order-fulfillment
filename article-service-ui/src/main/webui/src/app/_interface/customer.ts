export interface Customer {
  id: number,
  customerId?: string;
  name?: string;
  email?: string;
  shippingAddress?: Address;
  billingAddress?: Address;

}

export interface Address {
  street: string;
  city: string;
  state: string;
  zipCode: number;
  country: string;
}

export function addressToString(address: Address | undefined) {
  if (address) {
    return address.street + " " + address.city + " " + address.zipCode + address.country;
  }
  return '';

}
