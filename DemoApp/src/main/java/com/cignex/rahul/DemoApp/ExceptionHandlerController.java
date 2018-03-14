package com.cignex.rahul.DemoApp;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cignex.rahul.DemoApp.utils.CustomError;

@RestController
public class ExceptionHandlerController implements ErrorController {

	
	  @RequestMapping
	  public ResponseEntity<?> error() {
	    System.out.println("PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
	    return new ResponseEntity(new CustomError("sasasasasaasa"),HttpStatus.NOT_FOUND);
	  }
	
	  
	  @Override
		public String getErrorPath() {
			System.out.println("getErrorPath:   ");
			return "/error/404";
		}

	  

}