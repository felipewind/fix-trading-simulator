import { SessionStorage } from './session-storage.model';
import { SessionSettingsProperties } from './session-settings-properties.model';

export interface Session {
    acceptorStarted?: boolean,
    sessionSettingsFile?: string[],
    loggedOn?: boolean,
    startTime?: string,
    sessionID?: string,
    sessionSettingsProperties?: SessionSettingsProperties[],
    sessionStorage?: SessionStorage
}