import {Component} from '@angular/core';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

  bannerImgs = [
    {
      id: 1,
      img: '../../assets/images/banner/sdfr-women-foreveryrun.webp',
    },
    {
      id: 2,
      img: '../../assets/images/banner/sdfr-for-every-run-men-new-ladscape.webp',
    },
    {
      id: 3,
      img: '../../assets/images/banner/sdde-sneakers-spring-landscape.webp',
    },
  ];


}
