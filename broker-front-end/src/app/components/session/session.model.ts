import { SessionSettingsProperties } from './session-settings-properties.model';

export interface Session {
    initiatorStarted?: boolean,
    sessionSettingsFile?: string[],
    loggedOn?: boolean,
    startTime?: string,
    sessionID?: string,
    sessionSettingsProperties?: SessionSettingsProperties[]
}