# e-business
Repository containing tasks required for e-business classes.

## Docker task 1
### Requirements:
Ubuntu 24.04 image with python 3.10, Java in version 8 and Kotlin. ✅<br/>
Added gradle and JDBC SQLite package. ✅<br/>
Created simple Hello World program in java and executed by using build.gradle. ✅<br/>
Added docker-compose ✅<br/>
Location of image:
[Docker Image](https://hub.docker.com/repository/docker/pandoraproject/e-business/general)

## Docker task 2
### Requirements:
3.0 Należy stworzyć kontroler do Produktów ✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/b2e689bfa148baeec9d0ac839a2044a31f4cc10c) <br/>
3.5 Do kontrolera należy stworzyć endpointy zgodnie z CRUD - dane
pobierane z listy ✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/b2e689bfa148baeec9d0ac839a2044a31f4cc10c) <br/>
4.0 Należy stworzyć kontrolery do Kategorii oraz Koszyka + endpointy
zgodnie z CRUD ✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/7adf1ef989288b8467f8a8da594271885ac96637) <br/>
4.5 Należy aplikację uruchomić na dockerze (stworzyć obraz) oraz dodać
skrypt uruchamiający aplikację via ngrok ✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/5d82f2fbd6d1fff34bf37f5d9f91923b71864b31) <br/>
5.0 Należy dodać konfigurację CORS dla dwóch hostów dla metod CRUD ❌ <br/>
[Docker Image](https://hub.docker.com/repository/docker/pandoraproject/scala-crud/general)

## Docker task 3
### Requirements:
3.0 Należy stworzyć aplikację kliencką w Kotlinie we frameworku Ktor,
która pozwala na przesyłanie wiadomości na platformę Discord✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/fbac22d4dfe92683b1e6492eeb34c475aab864c4)<br/>
3.5 Aplikacja jest w stanie odbierać wiadomości użytkowników z
platformy Discord skierowane do aplikacji (bota)✅ [link to commit](https://github.com/Pandora-Project/e-business/commit/fbac22d4dfe92683b1e6492eeb34c475aab864c4)<br/>
4.0 Zwróci listę kategorii na określone żądanie użytkownika✅[link to commit](https://github.com/Pandora-Project/e-business/commit/6a42513d11c85a82ab87c34f2c1183093602a380)<br/>
4.5 Zwróci listę produktów wg żądanej kategorii✅[link to commit](https://github.com/Pandora-Project/e-business/commit/6a42513d11c85a82ab87c34f2c1183093602a380)<br/>
5.0 Aplikacja obsłuży dodatkowo jedną z platform: Slack, Messenger,
Webex❌<br/>

## Docker task 4
### Requirements:
3.0 Należy stworzyć aplikację we frameworki echo w j. Go, która będzie
miała kontroler Produktów zgodny z CRUD✅<br/>
3.5 Należy stworzyć model Produktów wykorzystując gorm oraz
wykorzystać model do obsługi produktów (CRUD) w kontrolerze (zamiast
listy)✅<br/>
4.0 Należy dodać model Koszyka oraz dodać odpowiedni endpoint✅<br/>
4.5 Należy stworzyć model kategorii i dodać relację między kategorią,
a produktem✅<br/>
5.0 pogrupować zapytania w gorm’owe scope'y✅<br/>
[link to commit](https://github.com/Pandora-Project/e-business/commit/36cc40a43fb0875538727a74edd681f858cf850d)

## Docker task 5
### Requirements:
3.0 W ramach projektu należy stworzyć dwa komponenty: Produkty oraz
Płatności; Płatności powinny wysyłać do aplikacji serwerowej dane, a w
Produktach powinniśmy pobierać dane o produktach z aplikacji
serwerowej ✅<br/>
3.5 Należy dodać Koszyk wraz z widokiem; należy wykorzystać routing ✅<br/>
4.0 Dane pomiędzy wszystkimi komponentami powinny być przesyłane za
pomocą React hooks ✅<br/>
4.5 Należy dodać skrypt uruchamiający aplikację serwerową oraz
kliencką na dockerze via docker-compose✅<br/>
5.0 Należy wykorzystać axios’a oraz dodać nagłówki pod CORS ✅<br/>
[link to commit](https://github.com/Pandora-Project/e-business/commit/090e5de4d02b8bd97cec63e206e1695723cf2033)
