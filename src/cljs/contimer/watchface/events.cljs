(ns contimer.watchface.events
  (:require [re-frame.core :as re]))

(re/reg-event-db
  ::timer-tick
  (fn [db]
    (println "tick")
    db))

(re/reg-event-fx
  ::start-timer
  (fn [{:keys [db]}]
    (let [time (:time db)
          minutes (:minutes time)
          seconds (:seconds time)]
      (println (str minutes " " seconds)))
    {:db       (assoc-in db [:time :started?] true)
     :interval {:action    :start
                :id        :timer-ticker
                :frequency 1000
                :event     [::timer-tick]}}))

(re/reg-event-fx
  ::stop-timer
  (fn [{:keys [db]}]
    {:db       (assoc-in db [:time :started?] false)
     :interval {:action :stop
                :id     :timer-ticker}}))