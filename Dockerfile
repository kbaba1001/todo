FROM clojure:lein-2.8.1
ENV LANG C.UTF-8

ENV APP_HOME /todo
RUN mkdir $APP_HOME
WORKDIR $APP_HOME

ADD . $APP_HOME
