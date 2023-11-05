## Arquitetura dos pacotes

```bash
src
└── br.com.bln.estudospringbatch
    └── adapter
        └── entrypoint
            └── controller (endpoint da aplicação)         
        └── gateway
            └── dataprovider (comunicação com os bancos de dados)     
    └── application
        └── commandline (commandline que serão executados assim que a aplicação for iniciada)                   
        └── configuration (configurações do spring)
        └── utils
    └── domain
        └── batchs (batchs do spring batch)
        └── entity (Entidades do ORM)                  
```
