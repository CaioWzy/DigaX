#Diga Xis  
> Pequeno exemplo de como exibir tirar foto da Webcam com JavaFX + WebCam Capture  
  
# Execução  
Para executá-lo é necessário ter  OpenJDK 11 instalado.

# Compilação
  
Este projeto utiliza o maven para o gerenciamento e empacotamento de dependências. 

Para instalar o maven:

```bash
# apt install -y maven
```
Para instalar o OpenJDK 11:

```bash
# apt install -y openjdk-11-jre openjdk-11-jdk
```
Para compilar e empacotar as dependências do projeto:
```bash
mvn clean compile package
```  

Se tudo ocorrer bem arquivo gerado com todas as dependências necessárias estará em shade/DigaXis.jar
  
